# Write your solution here
preptime(layers) = layers*2

remaining_time(ovenTime) = 60-ovenTime

total_working_time(layers, ovenTime) = ovenTime + preptime(layers)