var tmpl = (function() {
    var _tmpl = {};

    var tmplMap = {};

    _tmpl.scrape = function()
    {
        var tmpls = document.getElementsByClassName('tmpl');

        for (var i = 0; i < tmpls.length; i++) {
            var t = tmpls[i];

            tmplMap[t.id] = cleanHTML(t.innerHTML);
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