package com.josedev.colombiapp.permissions

class PostNotificationsPermissionsProvider: PermissionsProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if(isPermanentlyDeclined) {
            return "It seems you permanently declined notifications permissions. " + "You can enable them in the app settings."
        } else{
            "Keep updated with the latest updates of the app."
        }
    }
}