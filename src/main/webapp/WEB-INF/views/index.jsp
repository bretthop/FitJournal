<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FitJournal</title>

    <script type="text/javascript" src="res/js/lib/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="res/js/lib/underscore.js"></script>
    <script type="text/javascript" src="res/js/tmpl.js"></script>

    <link media="all" rel="stylesheet" type="text/css" href="res/css/fitJournal.css" />
</head>
<body>
<h1>Fit Journal</h1>
<button onclick="showAddMealDialog()" class="iblock">Add Meal</button>
<button onclick="showAddWorkoutDialog()" class="iblock">Add Workout</button>
<!-- TODO: Make these values dynamic (make them for the current week) -->
<label for="startDate">Start Date:</label>
<input type="text" id="startDate" value="2013-10-28 00:00:00"/>
<label for="endDate">End Date:</label>
<input type="text" id="endDate" value="2013-11-03 23:59:59"/>
<button onclick="document.location.reload()">Reload</button>
<div class="totalEnergyHolder">
    This week net energy: <span class="currentPeriodTotalEnergy">-</span>
    Total net energy: <span class="totalEnergy">-</span>
</div>
<div class="totalEnergyHolder">
    This week total protein: <span class="currentPeriodTotalProtein">-</span>
    Total protein: <span class="totalProtein">-</span>
</div>
<div id="daysContainer">
    <div id="dayTmpl" class="tmpl">
        || _.forEach(days, function(day) { ||
        <div class="iblock fleft dayHolder">
            <h2>{{ day.title }}</h2>

            || _.forEach(day.entries, function(entry) { ||
            <div class="iblock entryHolder">
                {{ entry.formattedTime }} <span class="pointer red" onclick="deleteEntry('{{entry.id}}')">X</span>
                <div class="iblock pointer" onclick="editEntry('{{entry.id}}')">
                    <span>Name: {{ entry.name }}</span>
                    <span>Energy: <span class="{{ entry.colourCss }}">{{ entry.kj }}</span></span>
                    || if (entry.type == 'MEAL') { ||
                    <span>Protein: {{ entry.protein }}</span>
                    || } ||
                </div>
            </div>
            || }); ||

            <div class="dayTotal">
                Net KJ: <span class="{{ day.colourCss }}">{{ day.netKj }}</span>
            </div>
            <div class="dayTotal">
                Total Protein: {{ day.totalProtein }}
            </div>
        </div>
        || }); ||
        <div class="cboth"></div>
    </div>
    <div id="addEntryDialogTmpl" class="tmpl">
        <div id="addEntryDialog" class="hidden">
            <h3>Add {{ entry.type.toLowerCase()  }}</h3>
            <input type="hidden" id="entryId" value="{{ entry.id }}" />
            <input type="hidden" id="entryType" value="{{ entry.type }}" />
            <input type="hidden" id="entryModifier" value="{{ entry.modifier }}" />
            <label for="entryTime">Time:</label>
            <input id="entryTime" type="text" value="{{ entry.entryTime }}" />
            <label for="entryName">Name:</label>
            <input id="entryName" type="text" value="{{ entry.name }}" />
            <label for="entryKj">Kj:</label>
            <input id="entryKj" type="text" value="{{ entry.kj }}" />
            || if (entry.type == 'MEAL') { ||
            <label for="entryProtein">Protein:</label>
            <input id="entryProtein" type="text" value="{{ entry.protein }}" />
            || } ||
            <button onclick="hideEntryDialog()">Cancel</button>
            <button onclick="addEntry()">{{ mode }}</button>
        </div>
    </div>
</div>

<script type="text/javascript">
    function deleteEntry(id)
    {
        if (confirm('Are you sure you want to delete this item?')) {
            $.ajax({url: '/v2/api/journal/' + id, type: 'DELETE', success: function() {
                location.reload();
            }});
        }
    }

    function editEntry(id)
    {
        $.ajax({url: '/v2/api/journal/' + id, success: function(entry){
            showAddEntryDialog(entry, 'Update');
        }});
    }

    function showAddMealDialog()
    {
        var entry = { type: 'MEAL', modifier: '',  entryTime: formatDate(new Date()) };
        showAddEntryDialog(entry, 'Add');
    }

    function showAddWorkoutDialog()
    {
        var entry = { type: 'WORKOUT', modifier: '-',  entryTime: formatDate(new Date()) };
        showAddEntryDialog(entry, 'Add');
    }

    function showAddEntryDialog(entry, mode)
    {
        var addEntryTmpl = tmpl.find('addEntryDialogTmpl');
        document.getElementsByTagName('body')[0].innerHTML += _.template(addEntryTmpl, { entry: entry, mode: mode });

        $('#addEntryDialog').show();
    }

    function addEntry()
    {
        var type = 'POST';
        var url = '/v2/api/journal';
        var data = {
            id: entryId.value,
            type: entryType.value,
            name: entryName.value,
            kj: entryModifier.value + entryKj.value,
            protein: typeof(entryProtein) !== 'undefined' ? entryProtein.value : '', // protein is not always available (workout)
            entryTime: entryTime.value
        };

        $.ajax({type: type, url: url, data: JSON.stringify(data), contentType: 'application/json', success: function() { location.reload(); }});

        hideEntryDialog();
    }

    function hideEntryDialog()
    {
        $('#addEntryDialog').remove();
    }

    function getJournalPeriod()
    {
        var data = {
            startDate: document.getElementById('startDate').value,
            endDate: document.getElementById('endDate').value
        };

        $.ajax({url: '/v2/api/journalPeriod', data: data, success: function(period){
            addAdditionalProps(period);

            var dayTmpl = tmpl.find('dayTmpl');
            daysContainer.innerHTML += _.template(dayTmpl, {days: period.days});

            $('.currentPeriodTotalEnergy').html(period.netKj).addClass(period.colourCss);
            $('.currentPeriodTotalProtein').html(period.totalProtein);
        }});
    }

    // At the moment this is only being used to get the total energy for the full period
    // This can be optimised
    function getFullJournalPeriod()
    {
        $.ajax({url: '/v2/api/journalPeriod', success: function(period){
            addAdditionalProps(period);

            $('.totalEnergy').html(period.netKj).addClass(period.colourCss);
            $('.totalProtein').html(period.totalProtein);
        }});
    }

    function addAdditionalProps(period)
    {
        _.forEach(period.days, function(day) {
            _.forEach(day.entries, function(entry) {
                entry.colourCss = entry.kj > 0 ? 'red' : 'green';
            });

            day.colourCss = day.netKj > 0 ? 'red' : 'green';
        });

        period.colourCss = period.netKj > 0 ? 'red' : 'green';
    }

    function formatDate(date)
    {
        return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate() + ' 00:00:00';
    }
</script>

<script type="text/javascript">
    _.templateSettings = {
        interpolate: /\{\{(.+?)\}\}/g,
        evaluate: /\|\|(.+?)\|\|/g
    };

    tmpl.scrape();

    getJournalPeriod();
    getFullJournalPeriod();
</script>
</body>
</html>