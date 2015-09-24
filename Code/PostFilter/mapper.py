#!/usr/bin/python

import sys
import os
from xml.dom import minidom
import datetime

# input comes from STDIN (stream data that goes to the program)
for line in sys.stdin:
	
	line = line.strip()
	try:
		xmldoc = minidom.parseString(line)
	except:
		continue
	try:
		itemlist = xmldoc.getElementsByTagName('row')
		for s in itemlist:
			if int(s.attributes['PostTypeId'].value) == 1:
			
				date,time = (s.attributes['CreationDate'].value).split('T')
				year,month,day = date.split('-')
				print "%s |-| %s |-| %s |-| %s |-| %s |-| %s |-| %s" %(s.attributes['PostTypeId'].value,s.attributes['Tags'].value,s.attributes['AnswerCount'].value,s.attributes['ViewCount'].value,s.attributes['Score'].value,s.attributes['Title'].value,year)
	except:
		continue
	
