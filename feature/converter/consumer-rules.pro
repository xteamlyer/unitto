# https://issuetracker.google.com/u/2/issues/371227633
# https://github.com/Kotlin/kotlinx.serialization/issues/2825
-keep @kotlinx.serialization.Serializable class * {*;}

# enum is used in route which is serialazble
-keep class com.sadellie.unitto.core.model.converter.UnitGroup
