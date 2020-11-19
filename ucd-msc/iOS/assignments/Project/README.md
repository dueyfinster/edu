Project Proposal
==============
for module COMP41550, by *Neil Grogan* 13204052

## Idea
My idea is an application for [Grogan Burner Services][] (my father's business). [An already existing Android application is available][Android App]. I plan to implement the iOS version similiar to the Android App with the following features:

+ Contact Us - call NOW or input form which can use email/sms
+ See Price list (Oil, Gas, BER[^1] )
+ See Live Location of you and GBS Engineer (the Office location will also be shown)
+ My Info - shows name/address/email/phone (possible Facebook/Twitter Integration)
+ My Info - show Boiler efficiency chart

## Implementation / Challenge

The Android apps use a webview to load the price lists in a browser - this is ugly and non-native. My plan for the iOS version is use a native interface everywhere. Since the prices are only published via the web, I will have to scrape it. As part of this I'll have to figure out if they have changed etc. 

[Grogan Burner Services]: http://www.groganburners.ie
[Android App]: https://play.google.com/store/apps/details?id=ie.groganburners.gbsapp

[^1]: BER is building energy rating
