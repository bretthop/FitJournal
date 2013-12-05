<%@ include file="header.jsp" %>
<h1>Weight Management</h1>
<div class="container">
    <label for="targetWeight">Target Weight:</label>
    <input id="targetWeight" name="targetWeight" type="text" />
    <button class="btn" onclick="saveTargetWeight()">Save!</button>
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

    function getTargetWeight()
    {
        var type = 'GET';
        var url = '/fitJournal/api/weight';

        $.ajax({type: type, url: url, success: function(data) { targetWeight.value = data.target; }});

    }
</script>

<script type="text/javascript">
    getTargetWeight();
</script>
<%@ include file="footer.jsp" %>