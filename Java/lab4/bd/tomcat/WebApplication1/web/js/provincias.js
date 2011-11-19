Ext.require([
		'Ext.grid.*',
	  'Ext.data.*',
    'Ext.util.*',
    'Ext.state.*'
]);
Ext.define('MProvincias', {
    extend: 'Ext.data.Model',
    fields: [
       'fid',
       {name: 'nombre', type: 'string'}
    ],
    idProperty: 'fid'
});

var stProv = Ext.create('Ext.data.Store', {
    model: 'MProvincias',
		autoLoad:true,
    proxy: {
			url: 'srvProvincias',
			simpleSortMode:true,
			type: 'ajax', // esto es extremadamente importante, porque sino, si ponemos jsonp necesitamos pasar el json de otra manera.. muy fea.
			reader:{
				type:'json',//esto tamb , extremadamente importante.
				root: 'root',
				totalProperty: 'totalCount'
			}
	}
});
// create the Grid
    var gProvinciasConf = {
			id:'migrid',
			width: 700,
		height: 500,
		title: 'Provincia',
        store: stProv,
        model:'MProvincias',
				columns:[
						{text:'id', dataIndex:'fid'},
						{text:'Nombre', dataIndex:'nombre'}
						
				],
        //renderTo: 'grid-example',
				viewConfig: {
					trackOver: false
		}
    };