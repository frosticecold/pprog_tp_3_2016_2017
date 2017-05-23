package utils;

/**
 *
 * @author Raúl Correia 1090657@isep.ipp.pt
 */
public interface InterfaceListaLerConfig<T> {
    
    /**
     * Adiciona um objeto genérico definida na interface
     * @param obj Objeto a adicionar
     * @return Retorna verdadeiro ou falso se foi adicionado
     */
    public boolean add(T obj);
    
}
