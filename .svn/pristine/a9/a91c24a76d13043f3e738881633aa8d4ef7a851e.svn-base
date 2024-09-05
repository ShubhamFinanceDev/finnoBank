$(function () {
    $('.btn').on('click', function () {
        var color = $(this).data('color');
       // alert(this.id);
        var array = this.id.split('@');
        $("#appid").val(array[0]);
        $("#legalstatus").val(array[1]);
        $("#technicalstatus").val(array[2]);
        $("#pastatus").val(array[3]);
        $("#docketstatus").val(array[4]);
        $("#distatus").val(array[5]);
        $("#finalstatus").val(array[6]);
        $("#didateifdoable").val(array[7]);
        $("#additionalremarks").val(array[8]);        
        document.getElementById("appid").value=array[0];
        document.getElementById("didateifdoable").value=array[7];
        document.getElementById("additionalremarks").value=array[8];
        $('#mdModal .modal-content').removeAttr('class').addClass('modal-content modal-col-' + color);
        $('#mdModal').modal('show');
    });
});