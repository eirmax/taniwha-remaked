package party.lemons.taniwha.registry;

public record ModifierContainer<T>(T type, Modifier<T>... modifiers)
{
    public ModifierContainer
    {
    }

    public void initModifiers()
    {
        for (Modifier<T> modifier : modifiers)
            modifier.accept(type);
    }
}
