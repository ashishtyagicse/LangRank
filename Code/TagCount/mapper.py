#!/usr/bin/python

import sys
import os

#languages = ['c#','java','php']


#i = 0
# input comes from STDIN (stream data that goes to the program)
lines = ['1 |-| <sql-server><database><svn><version-control> |-| 26 |-| 47930 |-| 198 |-| How do I version my SQL Server database |-| 2008']
for line in sys.stdin:
	
	try:	
		line = line.strip()
	
		row =  line.split('|-|')
		#print row
	
    		tagString = row[1].replace('><','$$')	
		tags = tagString.split('$$')
		for tag in tags:
		 	tag = tag.replace('<','')
		 	tag = tag.replace('>','')
		 	tag = tag.strip()
					#if int(row[4]) >= 0:
		 	print "%s,%d,%s,%s,%s" %(row[6].strip() + "$$"  +  tag ,1,row[2].strip(),row[3].strip(),row[4].strip())
			
	except:
		continue
			
			
	
