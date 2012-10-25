<?xml version='1.0' encoding='ISO-8859-1'?>
<!-- Generated with NCL API -->

<ncl id='jukilo' xmlns='http://www.ncl.org.br/NCL3.0/EDTVProfile'>
	<head>
		<regionBase>
			<region id='rgTV' left='0' top='0' height='100.0%' width='100.0%' zIndex='3'>
				<region id='rgProd' left='10.0%' top='5.0%' height='60.0%' width='40.0%' zIndex='4'/>
				<region id='rgPrec' right='30.0%' top='30.0%' height='20.0%' width='20.0%' zIndex='5'/>
				<region id='rgBot' right='45.0%' bottom='10' height='20.0%' width='30.0%' zIndex='6'/>
				<region id='rgResult1' left='10' top='10' height='45.0%' width='30.0%' zIndex='7'/>
				<region id='rgResult2' left='35.0%' top='10' height='45.0%' width='30.0%' zIndex='8'/>
				<region id='rgResult3' left='70.0%' top='10' height='45.0%' width='30.0%' zIndex='9'/>
			</region>
		</regionBase>
		<descriptorBase>
			<descriptor id='dpTV' region='rgTV'/>
			<descriptor id='dpProd' region='rgProd'/>
			<descriptor id='dpPrec' region='rgPrec'/>
			<descriptor id='dpResult1' region='rgResult1'/>
			<descriptor id='dpResult2' region='rgResult2'/>
			<descriptor id='dpResult3' region='rgResult3'/>
			<descriptor id='dpBot' region='rgBot' focusIndex='1'/>
			<descriptor id='dpAudio'/>
		</descriptorBase>
		<connectorBase>
                    <importBase alias='foo' documentURI='base.ncl'/>
                </connectorBase>
	</head>
	<body>
	</body>
</ncl>
