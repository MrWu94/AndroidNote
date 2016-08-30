// IPackageDataObserver.aidl
package android.content.pm;

// Declare any non-default types here with import statements


   /**
    * API for package data change related callbacks from the Package Manager.
    * Some usage scenarios include deletion of cache directory, generate
    * statistics related to code, data, cache usage(TODO)
    * {@hide}
    */
   oneway interface IPackageDataObserver {
       void onRemoveCompleted(in String packageName, boolean succeeded);
   }