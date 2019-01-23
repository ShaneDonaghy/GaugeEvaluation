Admin Add/Remove Products Functionality
=======================================
Created by shane on 25/07/2018

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

Both of these scenarios feed into eachother nicely, and dont cause a clogging-up of the store or depleting of actual stock
 § Admin adds a product in the first scenario
 § Admin removes a product in the second scenario

        |title|description|author|price|featured|date|img|
        |Test Book - Shanes Testing Bible|The Gospel of testing|The Prophet Shane Donaghy|34.99|true|29/June/2014|/img/bible.jpg|
        |Test Book - Testing with no code|How to test when you have no access to source|Sheppy-D|45.99|false|26/July/2018|/img/codeless.jpg|


* User opens the active admin application

## Admin Adds a Product

tags: add, product, admin

* Admin opens administration interface
* Admin opens the products tab
* Admin clicks the new product button
* Admin enters the title <title>
* Admin enters the description <description>
* Admin enters the author <author>
* Admin enters the price at <price>
* Admin toggles featured attribute <featured>
// DATE STRICTLY IN FORMAT: DD/Month/YYYY
* Admin adds date <date>
* Admin adds image file path <img>
* Admin clicks add product
* Admin sees product added confirmation message
* Admin closes window


## Admin Removes a Product

tags: remove, product, admin

* Admin opens administration interface
* Admin opens the products tab
* Admin selects a test book
* Admin deletes test book
* Admin accepts confirmation alertbox
* Admin sees product removed confirmation message
* Admin closes window