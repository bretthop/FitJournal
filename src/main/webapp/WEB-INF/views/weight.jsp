<%@ include file="header.jsp" %>
<h1>Weight</h1>
<div class="container">
    <label for="targetWeight">Target Weight:</label>
    <input id="targetWeight" name="targetWeight" type="text" />
    <button class="btn" onclick="saveTargetWeight()">Save!</button>
</div>
<div class="container">
    <label for="actualWeight">Actual Weight:</label>
    <input id="actualWeight" name="actualWeight" type="text" readonly="true" />
</div>
<div class="container">
    <button class="btn btn-large" onclick="document.location = '/fitJournal/syncFitbitWeight'">Fetch FitBit Data!</button>
    <button class="btn btn-warning" onclick="deleteFitBitData()">Delete FitBit Data</button>
</div>
<script type="text/javascript">
    function saveTargetWeight()
    {
        var type = 'POST';
        var url = '/fitJournal/api/weight';
        var data = {
            target: targetWeight.value
        };

        $.ajax({type: type, url: url, data: JSON.stringify(data), contentType: 'application/json', success: function() { location.reload(); }});
    }

    function deleteFitBitData()
    {
        var type = 'DELETE';
        var url = '/fitJournal/api/weightLog';

        $.ajax({type: type, url: url, success: function() { location.reload(); }});
    }

    function getTargetWeight()
    {
        var type = 'GET';
        var url = '/fitJournal/api/weight';

        $.ajax({type: type, url: url, success: function(data) { targetWeight.value = data.target; }});

    }

    function getActualWeight()
    {
        var type = 'GET';
        var url = '/fitJournal/api/weightLog';

        $.ajax({type: type, url: url, success: function(data) {
            if (!data.weight) {
                data.weight = 'No Weight for today';
            }

            actualWeight.value = data.weight;
        }});

    }
</script>

<script type="text/javascript">
    getTargetWeight();
    getActualWeight();
</script>
<%@ include file="footer.jsp" %>