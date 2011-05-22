Yahoo Place Finder for Android
==============================

Instruction:
------------

1. Create new instance of YahooPlaceFinder.
2. YahooPlaceFinder has two constructors. You can use either:
   - Lat and Lng; or
   - Address
3. Assign the result as JSONObject object.
4. If it's success, the result will be not null. Otherwise, null.

example:
--------

        String lat = "-6.234567";
        String lng = "106.23123";
        
        YahooPlaceFinder ypf = new YahooPlaceFinder(lat, lng);
        JSONObject result = ypf.getReverseGeo();
        
        // you can check for the result here
        if (result != null) {
            // Success. result contains the information needed.
        } else {
            // failed.
        }
