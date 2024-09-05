$(function() {
	$('.js-basic-example').DataTable({
		responsive : true

	});

	// Exportable table
	$('.js-exportable').DataTable({
		dom : 'Bfrtip',
		responsive : true,

		columnDefs : [ {
			"targets" : [ 0 ],
			"visible" : false,
			"searchable" : false
		} ],
		buttons : [

		{
			extend : 'copyHtml5',
			text : '<i class="fa fa-clipboard"></i>Copiar',
			title : 'Administrator Reports',
			titleAttr : 'Copiar',
			className : 'btn btn-app export barras',
			exportOptions : {
				columns : ':not(.notexport)'
			}
		},

		{
			extend : 'excelHtml5',
			text : '<i class="fa fa-file-excel-o"></i>Excel',
			title : 'Administrator Reports',
			titleAttr : 'Excel',
			className : 'btn btn-app export excel',
			exportOptions : {
				columns : ':not(.notexport)'
			}
		}, {
			extend : 'csvHtml5',
			text : '<i class="fa fa-file-text-o"></i>CSV',
			title : 'Administrator Reports',
			titleAttr : 'CSV',
			className : 'btn btn-app export csv',
			exportOptions : {
				columns : ':not(.notexport)'
			}
		} ]
	});
});