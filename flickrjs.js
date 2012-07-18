/*
Código escrito & renovado por Alejandro Moënne el 31 de Enero del 2012 (01:50Hrs) [Erik: Gracias papá <3]
Base de 'jflickfeed' (Método antiguo: RSS) por Joel Sutherland (2009)
*/

(function($) {
	$.fn.jflickrfeed = function(settings, callback) {		
		settings = $.extend(true, {
			flickrSet: '72157628748254859',
			flickrKey: '126d9428fa129b7cf7c614b7c9af0325',
			useTemplate: true,
			itemTemplate: '',
			itemCallback: function(){}
		}, settings);

		var url = "http://api.flickr.com/services/rest/?format=json&method=flickr.photosets.getPhotos&photoset_id=" + settings.flickrSet + "&api_key=" + settings.flickrKey + "&jsoncallback=?";

		return $(this).each(function(){
			
			var $container = $(this);
			var container = this;

			$.getJSON(url, function(flickrData){
				
				var length = flickrData.photoset.photo.length;
				
				var rgx_b = new RegExp('{{image_b}}', 'g');
				var rgx_s = new RegExp('{{image_s}}', 'g');
				var rgx_title = new RegExp('{{title}}', 'g');
				
				for (i=0; i<length; i++) {
					var photoURL = 'http://farm' + flickrData.photoset.photo[i].farm + '.' + 'static.flickr.com/' + flickrData.photoset.photo[i].server + '/' + flickrData.photoset.photo[i].id + '_' + flickrData.photoset.photo[i].secret +'_b.jpg'
					var thumbURL = 'http://farm' + flickrData.photoset.photo[i].farm + '.' + 'static.flickr.com/' + flickrData.photoset.photo[i].server + '/' + flickrData.photoset.photo[i].id + '_' + flickrData.photoset.photo[i].secret + '_s.jpg'
					var title = flickrData.photoset.photo[i].title;

					if(settings.useTemplate){
						var template = settings.itemTemplate;
						template = template.replace(rgx_b, photoURL);
						template = template.replace(rgx_s, thumbURL);
						template = template.replace(rgx_title, title);
						$container.append(template)
					}
				}
				if($.isFunction(callback)){
					callback.call();
				}
			});
		});
	}
})(jQuery);