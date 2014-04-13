import itertools
import visvis as vv
def getColor():
	while True: 
		for i in 'rbmyg':#wk
			yield i

def see(prob):
	app = vv.use()
	f = vv.clf()
	a = vv.cla()
	legends = []
	mx = 0
	my = 0
	pvars = prob.variables()
	colit = getColor()
	#windows first line
	for cons, color in itertools.izip(prob.constraints.values(), colit):
		vals = cons.values()
		px = ( float(-cons.constant)/cons.values()[0], 0)
		py = ( 0, float(-cons.constant)/cons.values()[1] )
		mx = max(mx, px[0])
		my = max(my, py[1])
		vv.plot(px, py, lc=color, mc=color, ms='.')
		legends.append(str(cons))
		if color=='r':#nug
			vv.plot(px, py, lc=color, mc=color, ms='.')
			legends.append('')
	#draw bounds
	for var, col, i in itertools.izip(pvars, colit, range(len(pvars))):
		if var.upBound:
			p = var.upBound
			ps = [
				(p, p),
				(0, my if i==0 else mx)
			]
			if i>0: ps.reverse()
			px, py = ps
			vv.plot(px, py, lc=col, mc=color, ms='.')
			legends.append("%s<=%s"%(var , var.upBound))
	#objective
	ob = prob.objective
	obv = ob.values()
	z = float(obv[0]*pvars[0].value() + obv[1]*pvars[1].value())
	#z = a*x1+b*x2
	#z/a = x1 #if x2==0
	#z/b = x2 #if x2==0
	px = (0, z/obv[0])
	py = (z/obv[1], 0)
	color = colit.next()
	vv.plot(px, py, lc=color, mc=color, ms='.', ls='--')
	legends.append("Objective %s"%(ob))
	#optimal result point
	vv.plot((pvars[0].value(),), (pvars[1].value(),), lc='c', mc='c', ms='o', mw=12)
	optmessage = "Optimal %s=%s, %s=%s, z=%s"%(pvars[0],pvars[0].value(),pvars[1], pvars[1].value(), prob.objective.value())
	legends.append(optmessage)
	##
	xs = pvars[:2]
	a = vv.gca()
	a.legend = legends

	a.axis.showGrid = 1

	a.axis.xlabel = str(xs[0])
	a.axis.ylabel = str(xs[1])
	vv.title(optmessage)
	app.Run()