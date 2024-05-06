class store {
    constructor() {
        this.map = new Map();
    }

    /*  Insert a key into the store.
        If the key is already in the store, its value is incremented,
        indicating the frequency that the key has been added to the store

        @param key - the key to insert
    */
    addToStore(key) {
        if (this.map.has(key)) {
            let num = this.map.get(key);
            this.map.set(key, num + 1);
            // console.log("incremented in store: " + key + " " + this.map.get(key));
        } else {
            this.map.set(key, 1);
            // console.log("added to store: " + key + " " + this.map.get(key));
        }
    }

    /*  Transform the Store into a
        @returns a String that contains each value, in the following

        If the map contains:
        "bhietsch" 1
        "ottwiz"   2

        The string produced will be:
        "bhietsch:1
        ottwiz:2
        "
    */
    toString() {
        let string = "";

        this.map.forEach((value, key) => {
            string += key + ":" + value + "\n";
        });

        return string;
    }
}

module.exports = store;
