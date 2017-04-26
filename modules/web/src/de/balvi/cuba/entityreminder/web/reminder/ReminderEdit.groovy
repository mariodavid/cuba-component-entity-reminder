package de.balvi.cuba.entityreminder.web.reminder

import com.haulmont.cuba.core.entity.Entity
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.DatatypeFormatter
import com.haulmont.cuba.core.global.LoadContext
import com.haulmont.cuba.core.global.Metadata
import com.haulmont.cuba.gui.WindowManager
import com.haulmont.cuba.gui.components.*
import com.haulmont.cuba.gui.components.actions.BaseAction
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory
import de.balvi.cuba.entityreminder.entity.Reminder

import javax.inject.Inject

class ReminderEdit extends AbstractEditor<Reminder> {

    @Inject
    FieldGroup fieldGroup

    @Inject
    DataManager dataManager

    @Inject
    Metadata metadata

    @Inject
    private ComponentsFactory componentsFactory;

    @Inject
    DatatypeFormatter datatypeFormatter

    @Override
    void init(Map<String, Object> params) {
        super.init(params)
        setShowSaveNotification(true)
    }

    @Override
    protected void postInit() {
        super.postInit()

        FieldGroup.FieldConfig entityField = fieldGroup.createField('entity')

        LinkButton entityFieldLinkButton = (LinkButton) componentsFactory.createComponent(LinkButton.NAME)

        entityFieldLinkButton.action = new BaseAction('reminderOpenEntity') {
            @Override
            void actionPerform(Component component) {
                openEditor(getEntityInstanceFromReminder(item) as Entity, WindowManager.OpenType.NEW_TAB)
            }
        }
        entityFieldLinkButton.caption = item.caption

        entityField.component = entityFieldLinkButton
        entityField.caption = 'Entity'
        entityField.enabled = false

        fieldGroup.addField(entityField,0,0);
    }

    Entity getEntityInstanceFromReminder(Reminder reminder) {
        LoadContext loadContext = new LoadContext(metadata.getClass(reminder.entityClass))
        loadContext.setId(reminder.entityId)
        dataManager.load(loadContext)
    }

    @Override
    protected boolean postCommit(boolean committed, boolean close) {
        if (committed) {
            showNotification(
                    formatMessage('reminderSavedMessage', item.instanceName, datatypeFormatter.formatDateTime(item.remindAt)),
                    Frame.NotificationType.TRAY);
        }
        return super.postCommit(committed, close)
    }
}