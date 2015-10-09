# authnr-permission-checker-api

An extension API of the ```PermissionChecker``` interface defined by the 
[authorization-api][1]. The differences:
 - The same methods are available without the ```authorizedResourceId``` 
 parameter, which is provided by the implementation of the 
 authnr-permission-checker-api.
 - A new method called ```checkPermission()``` introduced: it has the same 
 signature and behaviour as the ```hasPermission()``` method, but it throws 
 an exception in case of no permission available instead of a false return 
 value.

For more information and details check the javadoc of the 
___org.everit.authnr.permissionchecker.AuthnrPermissionChecker___ 
interface.

[![Analytics](https://ga-beacon.appspot.com/UA-15041869-4/everit-org/authorization-api)](https://github.com/igrigorik/ga-beacon)

[1]: https://github.com/everit-org/authorization-api
