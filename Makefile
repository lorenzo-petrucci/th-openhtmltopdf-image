test-out-compare: setup test-out
	firefox src/main/resources/template.html &

test-out-in: setup
	evince src/main/resources/out.pdf &
	mvn clean test
	evince src/main/resources/out.pdf &

test-out: test
	evince src/main/resources/out.pdf &

test: setup
	mvn clean test

setup:
	rm src/main/resources/out.pdf
	touch src/main/resources/out.pdf

clean:
	git restore src/main/resources/out.pdf
