/*
 *    Copyright 2016 Thomas Heinemann
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

#include <iostream>
#include <gtest/gtest.h>

#include <cstdlib>

#include <com.github.theinemann.jnitor.exampleClasses.ExampleClass.h>
#include <com.github.theinemann.jnitor.exampleClasses.SeparateExampleClass.h>
#include <com.github.theinemann.jnitor.exampleClasses.ExampleInterface.h>
#include <com.github.theinemann.jnitor.JnitorController.h>
#include <jni.h>



JavaVMOption options[1];
JNIEnv *env;
JavaVM *jvm;

int main(int argc, char** argv) {
#ifdef _WIN32
	options[0].optionString = "-Djava.class.path=..\\..\\classes\\test;..\\..\\..\\exampleClasses\\build\\classes\\main";
#else
	options[0].optionString = "-Djava.class.path=../../classes/test:../../../exampleClasses/build/classes/main";
#endif
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

/**
 * This test checks that the creation of a new object works correctly.
 */
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

/**
 * This test checks that calling a static method works correctly.
 */
TEST(jnitorTest, staticMethodTest) {
	ASSERT_DOUBLE_EQ(20.0, com::github::theinemann::jnitor::exampleClasses::ExampleClass::getSquareRoot(env, 400.0));
}

/**
 * This test checks that trying to access a class that is not in the classpath throws an exception
 * (but does not crash the program)
 */
TEST(jnitorTest, classNotInClassPathTest) {
	try {
		// JnitorController is part of the "main" classes (not test), so it is not in the classpath.
		com::github::theinemann::jnitor::JnitorController exampleObject(env);
		FAIL() << "Wrapper object was created without error, even though class is not in class path.";
	} catch (std::runtime_error& e) {
		return;
	}
}

TEST(jnitorTest, separateClassPathTest) {
	com::github::theinemann::jnitor::exampleClasses::SeparateExampleClass exampleObject(env);

	std::string helloWorld(env->GetStringUTFChars(exampleObject.greeting(),JNI_FALSE));
	ASSERT_EQ(std::string("Hello World"), helloWorld);
}
