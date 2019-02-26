clean:
	echo "we clean"
compile:
	echo "COMPILE STEP"
	
test:
	echo "TEST STEP"
package:
	echo "PACKAGE STEP"
	touch dir/target/1/touch.jar
	echo "TAG-LOCATION" > PACKAGE_LOCATION.txt
	echo "TAG-VERSION-1" > PACKAGE_VERSION.txt
