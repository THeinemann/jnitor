
#include <iostream>
#include <gtest/gtest.h>

#include <cstdlib>

#include <local.jnitor.ExampleClass.h>
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

TEST(jnitorTest, exampleClassTest) {
	local::jnitor::ExampleClass exampleObject(env);


	ASSERT_EQ(42, exampleObject.getMagicNumber());

	exampleObject.setMagicNumber(23);
	ASSERT_EQ(23, exampleObject.getMagicNumber());
}
