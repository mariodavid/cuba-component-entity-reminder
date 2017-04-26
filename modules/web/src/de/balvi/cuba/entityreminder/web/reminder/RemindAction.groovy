package de.balvi.cuba.entityreminder.web.reminder

import com.haulmont.chile.core.model.Session
import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.core.global.Metadata
import com.haulmont.cuba.gui.WindowManager
import com.haulmont.cuba.gui.components.Component
import com.haulmont.cuba.gui.components.actions.ItemTrackingAction
import de.balvi.cuba.entityreminder.entity.Reminder

class RemindAction extends ItemTrackingAction {

    Metadata metadata = AppBeans.get(Metadata)

    RemindAction() {
        super('remind')
        setCaption('Remind')
        setIcon('font-icon:BELL_O')
    }

    @Override
    void actionPerform(Component component) {
        def item = target.singleSelected

        def reminder = metadata.create(Reminder)

        Session session = metadata.session

        reminder.entityId = item.id as UUID
        reminder.entityClass = session.getClassNN(item.class).name
        reminder.caption = item.instanceName

        target.frame.openEditor(reminder, WindowManager.OpenType.DIALOG)
    }
}
