clean:
	echo "we clean"
compile:
	echo "COMPILE STEP"
	
test:
	echo "TEST STEP"
package:
	yum install -y ox-artifact
	echo "PACKAGE STEP"
	touch dir/target/1/touch.jar
	echo "dir/target/1/touch.jar" > PACKAGE_LOCATION.txt
	echo "TAG-VERSION-2" > PACKAGE_VERSION.txt
