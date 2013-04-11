from pybrain.datasets.classification import ClassificationDataSet
from pybrain.optimization.populationbased.ga import GA
from pybrain.tools.shortcuts import buildNetwork

from pybrain.supervised.trainers import BackpropTrainer
from pybrain.structure.modules   import SoftmaxLayer
from pybrain.utilities           import percentError
from pybrain.tools.shortcuts     import buildNetwork

ins = 4
outs = 1

classes = {
	'dunno':0.0,
	'Iris-setosa':1.0,
	'Iris-versicolor':2.0,
	'Iris-virginica':3.0,
}

# create XOR dataset
d = ClassificationDataSet(ins, outs, nb_classes=len(classes), class_labels=classes.keys())
for line in open('iris.csv', 'r').readlines():
	split =  line.strip().split(',') 
	raw = split[:ins]
	c = split[ins:]
	clas = classes.get(c[0], 0.0)
	data = [float(x) for x in raw if x != '']
	print data, clas
	d.addSample(data, [ clas ])
	
d.setField('class', [ [k] for k in classes.values()])

tstdata, trndata = d.splitWithProportion( 0.25 )
trndata._convertToOneOfMany( )
tstdata._convertToOneOfMany( )

print "Number of training patterns: ", len(trndata)
print "Input and output dimensions: ", trndata.indim, trndata.outdim
print "First sample (input, target, class):"
print trndata['input'][0], trndata['target'][0], trndata['class'][0]


fnn = buildNetwork( trndata.indim, 5, trndata.outdim, outclass=SoftmaxLayer )

trainer = BackpropTrainer( fnn, dataset=trndata, momentum=0.1, verbose=True, weightdecay=0.01)

for i in range(20):
	trainer.trainEpochs(1)
	trnresult = percentError( trainer.testOnClassData(),
                              trndata['class'] )
	tstresult = percentError( trainer.testOnClassData(
           dataset=tstdata ), tstdata['class'] )

	print "epoch: %4d" % trainer.totalepochs, \
          "  train error: %5.2f%%" % trnresult, \
          "  test error: %5.2f%%" % tstresult
out = fnn.activate(
	('vhigh','vhigh','2','2','small','low')
)
"""

nn = buildNetwork(ins, 3, 1)
ga = GA(d.evaluateModuleMSE, nn, minimize=True)
for i in range(100):
    nn = ga.learn(0)[0]
	print i"""

"""
# test results after the above script
In [68]: nn.activate([0,0])
Out[68]: array([-0.07944574])

In [69]: nn.activate([1,0])
Out[69]: array([ 0.97635635])

In [70]: nn.activate([0,1])
Out[70]: array([ 1.0216745])

In [71]: nn.activate([1,1])
Out[71]: array([ 0.03604205])"""