const parser = require("./parser");
const store = require("./store");

if (process.argv.length !== 4) {
    console.error("Usage: node driver.js <filename> <tag>");
    process.exit(1);
}

const filename = process.argv[2];
const tag = process.argv[3];

const myStore = new store();
const myParser = new parser(filename, myStore);

myParser.parse(tag);

console.log(myStore.toString());
