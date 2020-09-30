# TreeUritoAbsolutePath
How to get AbsolutePath from tree uri?
since api 19 android the "Intent.ACTION_OPEN_DOCUMENT" was introduced to pick a folder from using file manager. 
but this intent was depricated in android api 21 and new Intent was Introduced "Intent.ACTION_OPEN_DOCUMENT_TREE" (still working on api 30)
this Intent opens filemanager to choose Directory and by choosing the directory we can access uri path of directory.
but here was one problem that using this way the result string we get is tree uri and not the actual absolote path we want.
and it was little bit annoying for me when i was finding the way of how to convert tree uri to actual(or absolute- whatever you call).
so here i created one small function which finds the actual path from tree uri using string operations.

How it Works?

basically most of devices have the path that starts with /Storage/ prefix.
and the middle part of path contains mounted point name i.e /emulated/0/ for internal Storage, or some string like /C0V54440/ etc (just example).
and the last segment is path from root of storage to folder like /movie/piratesofthecarribian

so, the path we constructed from :-      /tree/primary:movie/piratesofthecarribian
is :-                                    /storage/emulated/0/movie/piratesofthecarribian

is it universal solution??

the question of that ans is simply No. as we all know its depends on manufacturer or vendor that what prefix they uses.
but Hold on i tested this solution in more than 1000 devices including emulators and tablets.
it is working in all devices as my experienc.(pls ping me if not works for your device).
i currently using this solution with my app FTP Server playstore link is
https://play.google.com/store/apps/details?id=com.hnc.ftpserver
