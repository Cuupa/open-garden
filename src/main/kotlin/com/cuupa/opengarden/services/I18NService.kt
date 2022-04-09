package com.cuupa.opengarden.services

import org.apache.commons.logging.LogFactory
import java.text.MessageFormat
import java.util.*


class I18NService {

    private var bundle: ResourceBundle? = null
    fun getLocale() = Locale.getDefault()
    fun isSupported(l: Locale) = Locale.getAvailableLocales().contains(l)
    fun setLocale(l: Locale?) {
        Locale.setDefault(l)
    }

    private fun getMessage(key: String): String {
        if (bundle == null) {
            bundle = ResourceBundle.getBundle(MESSAGES_KEY)
        }
        bundle?.let {
            if (it.containsKey(key)) {
                return it.getString(key)
            }
        }
        log.error("Can't determine translation for '$key'")
        return "UNDEFINED"
    }

    fun getMessage(key: String, vararg arguments: Any?): String {
        return MessageFormat.format(getMessage(key), *arguments)
    }

    fun getMessage(keys: List<String>, vararg arguments: Any?): List<String> {
        return keys.mapNotNull { getMessage(it, arguments) }
    }

    companion object {
        private const val MESSAGES_KEY = "messages"

        private val log = LogFactory.getLog(I18NService::class.java)
    }
}
