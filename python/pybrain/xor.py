from pybrain.datasets.classification import ClassificationDataSet
from pybrain.optimization.populationbased.ga import GA
from pybrain.tools.shortcuts import buildNetwork
ins = 6
outs = 1

classes = {
	'unacc':0.0,
	'acc':1.0,
	'good':2.0,
	'v-good':3.0
}
columns = [
	{},
	{},
	{},
	{},
	{},
	{},
]
# create XOR dataset
d = ClassificationDataSet(ins, outs)
d.addSample([0., 0.], [0.])
d.addSample([0., 1.], [1.])
d.addSample([1., 0.], [1.])
d.addSample([1., 1.], [0.])
d.setField('class', [[0.], [1.], [1.], [0.]])

nn = buildNetwork(2, 3, 1)
ga = GA(d.evaluateModuleMSE, nn, minimize=True)
for i in range(100):
    nn = ga.learn(0)[0]

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