package CSCI5308.GroupFormationTool.AccessControl;

import CSCI5308.GroupFormationTool.SystemConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivePasswordPolicyListBuilder implements IActivePasswordPolicyListBuilder
{
    public List<Context> createAllPasswordPolicyList(User user)
    {
        List<Context> contextList = new ArrayList<>();

        Context minLengthPolicy = new Context();
        minLengthPolicy.setStrategy(new MinimumLengthPolicy());
        contextList.add(minLengthPolicy);

        Context maxLengthPolicy = new Context();
        maxLengthPolicy.setStrategy(new MaximumLengthPolicy());
        contextList.add(maxLengthPolicy);

        Context minLowercase = new Context();
        minLowercase.setStrategy(new MinimumLowercasePolicy());
        contextList.add(minLowercase);

        Context minSymbolOrSpecialCharacter = new Context();
        minSymbolOrSpecialCharacter.setStrategy(new MinimumSymbolOrSpecialCharacterPolicy());
        contextList.add(minSymbolOrSpecialCharacter);

        Context minUppercase = new Context();
        minUppercase.setStrategy(new MinimumUppercasePolicy());
        contextList.add(minUppercase);

        Context passwordHistory = new Context();
        passwordHistory.setStrategy(new PasswordHistoryPolicy(user));
        contextList.add(passwordHistory);

        Context charactersNotAllowed = new Context();
        charactersNotAllowed.setStrategy(new CharactersNotAllowedPolicy());
        contextList.add(charactersNotAllowed);
        return contextList;
    }
}
