% (Demarcates the start and end of a program. Originally indicated the start and end of tape feed on NC machines, generally but not always required to be present on newer machines.)
N01 G96 S854 
N02 G00 X1.1 Z1.1 (Call tool radius offset. Call tool wear offset. Rapid feed to a point about 0.100" from the end of the bar [not counting 0.005" or 0.006" that the bar-pull-and-stop sequence is set up to leave as a stock allowance for facing off] and 0.050" from the side)
N03 G01 Z1.0 F.05 (Feed in horizontally until the tool is standing 1" from the datum i.e. program Z-zero)
N04 X-0.002 (Feed down until the tool is slightly past center, thus facing the end of the bar)
N05 G00 Z1.1 (Rapid feed 0.1" away from the end of the bar—clear the part)
N06 X1.0 (Rapid feed up until the tool is standing at the finished OD)
N07 G01 Z0.0 F.05 (Feed in horizontally cutting the bar to 1" diameter all the way to the datum, feeding at 0.050" per revolution)
N08 G00 X1.1 (Clear the part, stop the spindle, turn off the coolant)
N09 G91 X0 (Home X axis—return to machine X-zero passing through no intermediate X point [incremental X0])
N10 G91 Z0 (Home Z axis—return to machine Z-zero passing through no intermediate Z point [incremental Z0])
N11 M30 (Program stop, rewind to beginning of program)
%	