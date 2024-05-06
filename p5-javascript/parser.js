const fs = require('fs');

class parser {
    /*
        @param filename - a file with data that is assumed to be valid JSON
        @param store - a store object
    */
    constructor(filename, store) {
        this.file = filename;
        this.store = store;
        this.numberOfFeaturesChecked = 0;
    }

    /*  The parse method recursively traverses the JSON found in file,
        and if it finds a specific tag
     */
    parse(tag) {
        try {
            const data = fs.readFileSync(this.file, 'utf8');
            let obj = JSON.parse(data);
            this.recursiveSearch(obj, tag);
        } catch {
            console.error("Error reading file: " + this.file);
        }
    }

    /*  The recursiveSearch method is a helper method for the parse method.
        It recursively traverses the JSON object and checks for the tag.
        If the tag is found, it is added to the store.
     */
    recursiveSearch(obj, tag) {
        if (typeof obj === "object") {
            for (let key in obj) {
                if (key === "Feature") {
                    this.numberOfFeaturesChecked++;
                    // console.log("found feature: " + obj[key])
                }
    
                if (key === tag) {
                    // console.log("found tag: " + obj[key])
                    this.store.addToStore(obj[key]);
                }
    
                this.recursiveSearch(obj[key], tag);
            }
        }
    }

    /*  The toString method returns the number of features counted
    */
    toString() {
        return "Feature Count: " + this.numberOfFeaturesChecked;
    }
}

module.exports = parser;
