var tmpl = (function() {
    var _tmpl = {};

    var tmplMap = {};

    _tmpl.scrape = function()
    {
        var tmpls = document.getElementsByClassName('tmpl');

        for (var i = 0; i < tmpls.length;) {
            var t = tmpls[i];

            tmplMap[t.id] = cleanHTML(t.innerHTML);

            // TODO: Find the vanilla js way to do this
            $(t).remove(); // Also, remove() removes this el from the tmpls array, so we are always working with index 0
        }
    };

    _tmpl.find = function(tmplId)
    {
        var result = tmplMap[tmplId];

        if (!result) {
            console.log('Error: No template in map with id ' + tmplId);
        }

        return result;
    };

    _tmpl.all = function()
    {
        return tmplMap;
    };

    function cleanHTML(html)
    {
        return html ?
            html
                .replace(/&lt;/g, '<')
                .replace(/&gt;/g, '>')
                .trim()
            : '';
    }

    return _tmpl;
})();