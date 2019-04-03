clean:
	echo "we clean"
	whoami
compile:
	echo "COMPILE STEP"
	
test:
	echo "TEST STEP"
package:
	echo "PACKAGE STEP"
	touch dir/target/1/touch1.jar
	touch dir/target/1/touch2.jar
	echo "dir/target/1/touch1.jar" > PACKAGE_LOCATION.txt
	echo "dir/target/1/touch2.jar" >> PACKAGE_LOCATION.txt
	echo "TAG-VERSION-2" > PACKAGE_VERSION.txt
