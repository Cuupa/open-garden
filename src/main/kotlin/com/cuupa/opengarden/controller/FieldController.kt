package com.cuupa.opengarden.controller

import com.cuupa.opengarden.Search
import com.cuupa.opengarden.pojos.Field
import com.cuupa.opengarden.pojos.FieldType
import com.cuupa.opengarden.services.FieldDatabase
import com.cuupa.opengarden.services.I18NService
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class FieldController(val fieldsDB: FieldDatabase, val i18n: I18NService) {

    @GetMapping("/fields")
    fun fields(authentication: Authentication?): ModelAndView {
        val fields = fieldsDB.find("test")
        return ModelAndView("fields").apply {
            addObject("search", Search())
            if(fields.isEmpty()){
                addObject("message", i18n.get("has-no-fields"))
            } else {
                addObject("fields", fields)
            }
        }
    }

    @GetMapping("/fields/add")
    fun addFields(): ModelAndView {
        return ModelAndView("fields_add").apply {
            addObject("search", Search())
            addObject("textName", i18n.get("fields-add-name"))
            addObject("field", Field())

           val fieldTypes = FieldType.values().map { it.name }
            addObject("fieldTypeList", i18n.get(fieldTypes))
        }
    }
}