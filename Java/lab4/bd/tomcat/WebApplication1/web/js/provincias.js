Ext.require([
		'Ext.grid.*',
	  'Ext.data.*',
    'Ext.util.*',
    'Ext.state.*'
]);
Ext.define('MProvincias', {
    extend: 'Ext.data.Model',
    fields: [
       {name: 'fid',			type: 'int'},
       {name: 'nombre', type: 'string'}
    ],
    idProperty: 'fid'
});

var stProv = Ext.create('Ext.data.Store', {
    id: 'store',
    model: 'Ext.MProvincias',
		autoLoad:true,
    proxy: {
       type: 'jsonp',
			 root: 'datos',
			 totalProperty: 'cuenta',
       url: 'srvProvincias'
	}
});
// create the Grid
    var gProvinciasConf = {
			id:'migrid',
			width: 700,
		height: 500,
		title: 'provincias',
        store: stProv,
        model:'MProvincias',
				columns:[{text:'laskd', dataIndex:'nombre'}],
        renderTo: 'grid-example',
				viewConfig: {
					trackOver: false
		}
    };