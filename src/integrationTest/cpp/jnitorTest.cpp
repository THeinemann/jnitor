
#include <iostream>
#include <gtest/gtest.h>

#include <cstdlib>

#include <com.github.theinemann.jnitor.exampleClasses.ExampleClass.h>
#include <com.github.theinemann.jnitor.exampleClasses.ExampleInterface.h>
#include <jni.h>


JavaVMOption options[1];
JNIEnv *env;
JavaVM *jvm;

int main(int argc, char** argv) {
	options[0].optionString = "-Djava.class.path=../../classes/main:../../classes/test";
	JavaVMInitArgs vm_args;
	memset(&vm_args, 0, sizeof(vm_args));
	vm_args.nOptions = 1;
	vm_args.options = options;
	vm_args.version = JNI_VERSION_1_6;

	long status = JNI_CreateJavaVM(&jvm, (void**)(&env), &vm_args);

	if (status != JNI_OK) {
		std::cerr << "Error initializing the JVM\n";
		return -1;
	}

	::testing::InitGoogleTest(&argc, argv);
	int result = RUN_ALL_TESTS();
	jvm->DestroyJavaVM();
	return result;
}

TEST(jnitorTest, createObjectTest) {
	com::github::theinemann::jnitor::exampleClasses::ExampleClass exampleObject(env);


	ASSERT_EQ(42, exampleObject.getMagicNumber());

	exampleObject.setMagicNumber(23);
	ASSERT_EQ(23, exampleObject.getMagicNumber());
}

/**
 * This test checks that a Java object returned by a method can be wrapped correctly
 */
TEST(jnitorTest, receiveObjectTest) {
	com::github::theinemann::jnitor::exampleClasses::ExampleClass exampleObject(env, 1337);


	ASSERT_EQ(1337, exampleObject.getMagicNumber());

	com::github::theinemann::jnitor::exampleClasses::ExampleClass negatedExampleObject(env, exampleObject.negate());

	ASSERT_EQ(-1337, negatedExampleObject.getMagicNumber());
}

/**
 * This test checks that a Java object can be accessed through an interface wrapper (instead of an implementation class wrapper)
 */
TEST(jnitorTest, interfaceTest) {
	com::github::theinemann::jnitor::exampleClasses::ExampleClass exampleObject(env);

	com::github::theinemann::jnitor::exampleClasses::ExampleInterface exampleInterfaceObject(env, exampleObject.getExampleInterface());

	ASSERT_EQ(144, exampleInterfaceObject.doSomething(12));
}

TEST(jnitorTest, staticMethodTest) {
	ASSERT_DOUBLE_EQ(20.0, com::github::theinemann::jnitor::exampleClasses::ExampleClass::getSquareRoot(env, 400.0));
}
