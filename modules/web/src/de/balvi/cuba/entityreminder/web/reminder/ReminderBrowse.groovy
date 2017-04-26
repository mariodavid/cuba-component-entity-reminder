package de.balvi.cuba.entityreminder.web.reminder

import com.haulmont.cuba.core.entity.Entity
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.LoadContext
import com.haulmont.cuba.core.global.Metadata
import com.haulmont.cuba.gui.WindowManager
import com.haulmont.cuba.gui.components.AbstractLookup
import com.haulmont.cuba.gui.components.Component
import com.haulmont.cuba.gui.components.LinkButton
import com.haulmont.cuba.gui.components.Table
import com.haulmont.cuba.gui.components.actions.BaseAction
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory
import de.balvi.cuba.entityreminder.entity.Reminder
import groovy.transform.CompileStatic

import javax.inject.Inject

@CompileStatic
class ReminderBrowse extends AbstractLookup {

    @Inject
    Table<Reminder> remindersTable

    @Inject
    ComponentsFactory componentsFactory

    @Inject
    DataManager dataManager

    @Inject
    Metadata metadata

    @Override
    void init(Map<String, Object> params) {
        super.init(params)
        remindersTable.addGeneratedColumn('entity', new Table.ColumnGenerator<Reminder>() {
            @Override
            Component generateCell(Reminder reminder) {
                LinkButton button = (LinkButton) componentsFactory.createComponent(LinkButton.NAME)

                button.action = new BaseAction('reminderOpenEntity') {
                    @Override
                    void actionPerform(Component component) {
                        openEditor(getEntityInstanceFromReminder(reminder) as Entity, WindowManager.OpenType.NEW_TAB)
                    }
                }
                button.caption = reminder.caption

                button
            }

            Entity getEntityInstanceFromReminder(Reminder reminder) {
                LoadContext loadContext = new LoadContext(metadata.getClass(reminder.entityClass))
                loadContext.setId(reminder.entityId)
                dataManager.load(loadContext)
            }
        })
    }


    void markAsDone() {
        remindersTable.selected.each {
            it.done = true
        }
        dsContext.commit()
    }

}