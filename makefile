
JAVAC=javac
JAVA=java
JAR=jar

SRC_DIR=practica5/src
BIN_DIR=practica5/bin
LIB_DIR=practica5/lib
PACKAGE=pr2
MAIN_CLASS=$(PACKAGE).Graph
TEST_CLASS=$(PACKAGE).GraphTest
JUNIT_JAR=$(LIB_DIR)/junit-4.13.2.jar
HAMCREST_JAR=$(LIB_DIR)/hamcrest-core-1.3.jar
JAR_FILE=PRACTICA5/graph.jar

# Compilar el código fuente y las pruebas
compile:
	mkdir -p $(BIN_DIR)
	$(JAVAC) -d $(BIN_DIR) -cp $(SRC_DIR):$(JUNIT_JAR):$(HAMCREST_JAR) $(SRC_DIR)/$(PACKAGE)/*.java

# Ejecutar las pruebas unitarias
test: compile
	$(JAVA) -cp $(BIN_DIR):$(JUNIT_JAR):$(HAMCREST_JAR):$(SRC_DIR) org.junit.runner.JUnitCore $(TEST_CLASS)

# Generar el archivo JAR del proyecto
jar: compile
	$(JAR) cvfe $(JAR_FILE) $(MAIN_CLASS) -C $(BIN_DIR) .

# Limpiar los archivos generados
clean:
	rm -rf $(BIN_DIR) $(JAR_FILE)


