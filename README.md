This is a small tool to grab all the mix and match dakimakura thumbnails off of Cuddly Octopus' website.

It will download these thumbnail images to a folder on your local machine.

Please edit the following values at the top of Main.java before running the program.

maxSKUNumber - The Maximum SKU it will search for images. This will change over time as more dakimakuras are added to CO.

SKUsPerThread - How many SKUs you want per thread, the lower the number the more threads will be started to download images simultaneously, keep it to something reasonable like 300-500.

filePath - The file path of the folder you want the images downloaded to. For example on MacOS my file path is /Users/myusername/Pictures/CuddlyOctopusPics/

If you encounter errors with only a few downloads, it's probably working right. There is a special cases method to cover SKUs that seem to be missing mix and match thumbnails. If you encounter errors with every download, check the error message and make sure your file path is correct.

I am not affiliated with Cuddly Octopus, I made this because I was bored.
