package org.sa.rainbow.model.acme.znn.commands;

import org.acmestudio.acme.PropertyHelper;
import org.acmestudio.acme.element.IAcmeComponent;
import org.acmestudio.acme.element.IAcmeSystem;
import org.acmestudio.acme.element.property.IAcmeProperty;
import org.acmestudio.acme.model.command.IAcmePropertyCommand;
import org.sa.rainbow.core.error.RainbowModelException;

public class SetLoadCmd extends ZNNAcmeModelCommand<IAcmeProperty> {

    private String m_server;
    private float          m_load;

    public SetLoadCmd (String commandName, IAcmeSystem model, String server, String load) {
        super (commandName, model, server, load);
        m_server = server;
        m_load = Float.valueOf (load);
    }

    @Override
    protected void doConstructCommand () throws RainbowModelException {
        IAcmeComponent server = resolveInModel (m_server, IAcmeComponent.class);
        m_command = server.getCommandFactory ().propertyValueSetCommand (server.getProperty ("load"),
                PropertyHelper.toAcmeVal (m_load));
    }

    @Override
    protected IAcmeProperty getResult () {
        return ((IAcmePropertyCommand )m_command).getProperty ();
    }


}