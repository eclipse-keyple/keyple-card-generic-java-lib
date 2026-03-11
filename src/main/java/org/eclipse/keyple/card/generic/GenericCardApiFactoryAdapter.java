package org.eclipse.keyple.card.generic;

import org.eclipse.keypop.genericcard.CardTransactionManager;
import org.eclipse.keypop.genericcard.GenericCardApiFactory;
import org.eclipse.keypop.genericcard.GenericCardSelectionExtension;
import org.eclipse.keypop.reader.CardReader;
import org.eclipse.keypop.reader.selection.spi.SmartCard;

public class GenericCardApiFactoryAdapter implements GenericCardApiFactory {

    @Override
    public GenericCardSelectionExtension createGenericCardSelectionExtension() {
        return new GenericCardSelectionExtensionAdapter();
    }

    @Override
    public CardTransactionManager createCardTransaction(CardReader cardReader, SmartCard card) {
        return new CardTransactionManagerAdapter(cardReader, card);
    }
}
