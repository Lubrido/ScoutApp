
// Import and instantiate MySQL connector
const mysql = require('mysql');
const pool = mysql.createPool({
    host: "iosense.tudelft.nl",
    port: "3306",
    user: "user73",
    password: "C6goltZE",
    database: "database73",
    connectionLimit: 100,
    multipleStatements: true,
    debug: false
});


// Import web server library Express
const express = require('express');
const path = require('path');
// Define the web application with Express
const app = express();
// Add the ability to parse the request body (instead of the raw string)
const bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({limit: '50mb', extended: true}));
app.use(bodyParser.json({limit: '50mb'}));



/**
 * Execute an SQL query.
**/
function execSQL(sql, data) {
    // Build a promise (result via then(), error via catch())
    return new Promise((resolve, reject) => {
        pool.getConnection((error, connection) => {
            if (error) {
                // Write a log to keep track of errors
                console.error(error);
                // Reject the promise, reject will trigger the catch()
                reject(error);
            }
            const query = connection.query(sql, data, (error, result) => {
                connection.release();
                if (error) {
                    if (error.errno == 1062) { // MySQL error for duplicated entries
                        reject({error: "Already exist.", errno: 500});
                    } else {
                        // Write a log to keep track of errors
                        console.error(error);
                        // Reject the promise, reject will trigger the catch()
                        reject({error: 'Something wrong happened :(', errno: 500});
                    }
                }
                // Result from the database, resolve will trigger then()
                resolve(result);
            });
        });
    });
}


/**
 * My first API
**/

app.get('/', (request, response) => {
    response.send("Hello, world! 1st test V3");
    });


app.get('/Pictures', (request, response) => {
    const sql = 'SELECT `ID`, `Name` , `File Name` ,`Location Lat` ,`Location Long` FROM `Pictures` ORDER BY `ID`';
    // Send the query (without data) to be executed
    execSQL(sql).then( (result) => {
        response.send({Pictures: result});
    }).catch( (error) => {
        response.send(error);
        });
});


// Start the web server
app.listen(8081, 'localhost');
console.info('Running on http://localhost:8081');

