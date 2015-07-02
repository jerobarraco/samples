var _eta = {
	to: 1000,//timeout
	c: 0,//count
	st: 0, //start time
	s: 0,//sum
	l: 0,//last ms
	ld: 0,//last dur
	a: 0,//avg
	o: 0,//objective
	e: 0,//eta
	sp: 0,//speed
	cs: 0,//current sp
	ca: 0,//current avg
	ce: 0,//current eta
	timer: 0,
	stop: function(){
		clearTimeout(_eta.timer);
		_eta.timer = 0;
		document.getElementById("b_count").value = "Start";
	},
	tick: function(){
		_eta.timer = setTimeout(_eta.tick, _eta.to);
		var n =	+(new Date());
		_eta.cs = n - _eta.l;
		_eta.ca = (_eta.s + _eta.cs)/_eta.c;
		_eta.ce = (_eta.o -_eta.c)*_eta.ca;
		_eta.e = ((_eta.o -_eta.c)*_eta.a)-_eta.cs;
		_eta.show();
		//console.log("tick");
	},
	count: function(){
		var n = +(new Date());
		if(_eta.timer == 0) {
			_eta.reset();
			_eta.c = -1 ; //fact is , we are just starting, we can't count 1 yet (notice the +1 below)
		}
		_eta.ld = n - _eta.l;
		_eta.l = n;
		_eta.c += 1;
		_eta.s += _eta.ld;
		_eta.a = _eta.s/_eta.c;
		//_eta.e = (_eta.o -_eta.c)*_eta.a;
		_eta.show();
		if(_eta.c == _eta.o) _eta.stop();
	},
	reset: function(){
		_eta.stop();
		_eta.c = 0;
		_eta.e = 0;
		_eta.e = 0;//eta
		_eta.s = 0;//sum
		_eta.l = 0;//last ms
		_eta.ld = 0;//last_duration
		_eta.sp = 0;//speed
		_eta.cs = 0;//current sp
		_eta.ca = 0;//current avg
		_eta.ce = 0;//current eta
		_eta.o = parseInt(document.getElementById("cant").value);
		_eta.to = parseInt(document.getElementById("toms").value);
		_eta.l = +new Date();//gets current milliseconds. meh
		_eta.st = _eta.l;//start ms
		_eta.tick();
		document.getElementById("b_count").value = "Count";
	},
	ms2td: function(v, simple){
		v = Math.ceil(v);
		var t = "";
		
		var ms = v %1000;
		v -= ms;
		v /= 1000;
		
		var s = v %60;
		v -= s;
		v /= 60;
		
		var m = v %60;
		v -= m;
		v /= 60;
		
		var h = v %24;
		v -= h;
		v /= 24;
		
		t = h + ":" + m + ":" + s + "." + ms;
		if (simple){
			return v + "d " + t;
		}
		if(v>0){
			var d = v %5;
			v -= d;
			v /= 5;
			t = d + "d " + t;
		}
		if (v>0){
			var w = v %4;
			v -= w;
			v /= 4;
			t = w + "w " + t;
		}
		if (v>0){
			var mo = v %12;
			v -= mo;
			v /= 12;
			t = mo + "m " + t;
		}
		if (v>0){
			t = v + "y " + t;
		}
		return t;
	},
	simplify: function(v){
		var vv = 1.0/v;
		var t = "/ms";
		if (vv<25){
			vv *= 1000.0;
			t = "/s";
			if (vv<25){
				vv *= 60.0;
				t = "/m";
				if (vv<25){
					vv *= 60.0;
					t = "/h";
				}
			}
		}
		return [vv, t];
	},
	show: function(){
		var sa = _eta.simplify(_eta.a);
		var asp = sa[0];
		var aspt = sa[1];
		
		var ss = _eta.simplify(_eta.ld);
		var sp = ss[0];
		var spt = ss[1];

		var t = "";
		t += "Progress		: "+ _eta.o +" -"+(_eta.o-_eta.c) +" = "+_eta.c+"<br>";
		t += "ETA			: "+_eta.ms2td(_eta.e) +"<br>";
		t += "Avg. Dur.		: "+_eta.ms2td(_eta.a) +"<br>";
		t += "Last Dur.		: "+_eta.ms2td(_eta.ld) +"<br>";
		t += "Avg. Speed " + aspt +"	: "+asp+"<br>";
		t += "Last Speed " + spt +"	: "+sp+"<br>";
		t += "Sum			: "+_eta.ms2td(_eta.s, true) +"<br>";
		t += "--------------------------------------<br>";
		t += "CCurr			: "+_eta.ms2td(_eta.cs) +"<br>";
		t += "CAvg.			: "+_eta.ms2td(_eta.ca) +"<br>";
		t += "CETA			: "+_eta.ms2td(_eta.ce) +"<br>";
		t += "Start Time	: "+_eta.ms2td(_eta.st) +" ("+_eta.st+")<br>";
		document.getElementById("texto").innerHTML = t;
	},
	load: function(){
		_eta.reset();//last is set to current, thats ok
		_eta.c = parseInt(document.getElementById("start_count").value);
		_eta.st = parseInt(document.getElementById("start_ms").value);
		
		_eta.s = (_eta.l - _eta.st);//read comment above in reset();
		
		_eta.a = _eta.s/_eta.c;
		_eta.ld = _eta.a;
		
	}
};
