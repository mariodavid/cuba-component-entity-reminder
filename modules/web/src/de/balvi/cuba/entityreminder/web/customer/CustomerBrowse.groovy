package de.balvi.cuba.entityreminder.web.customer

import com.haulmont.cuba.gui.components.AbstractLookup
import com.haulmont.cuba.gui.components.Table
import de.balvi.cuba.entityreminder.entity.Customer
import de.balvi.cuba.entityreminder.web.reminder.RemindAction

import javax.inject.Inject

class CustomerBrowse extends AbstractLookup {

    @Inject
    Table<Customer> customersTable

    @Override
    void init(Map<String, Object> params) {

        customersTable.addAction(new RemindAction())
    }
}