# Gestión de Defectos - Registry

## Defecto 01
- **Caso:** persona con edad 17
- **Esperado:** `UNDERAGE`
- **Obtenido:** `INVALID`
- **Causa probable:** no existía distinción entre edad inválida y menor de edad en `RegisterResult`
- **Estado:** Cerrado ✅

## Defecto 02
- **Caso:** persona con id = 0 o id negativo
- **Esperado:** `INVALID`
- **Obtenido:** `VALID`
- **Causa probable:** falta de validación del campo `id` en `Registry.registerVoter`
- **Estado:** Cerrado ✅

## Defecto 03
- **Caso:** persona con edad 121
- **Esperado:** `INVALID`
- **Obtenido:** `VALID`
- **Causa probable:** falta de validación del límite superior de edad
- **Estado:** Cerrado ✅

## Defecto 04
- **Caso:** persona con nombre `'; DROP TABLE voters;--`
- **Esperado:** `INVALID`
- **Obtenido:** `VALID`
- **Causa probable:** ausencia de validación de caracteres especiales en el nombre, riesgo de SQL injection
- **Estado:** Cerrado ✅

## Defecto 05
- **Caso:** persona con nombre vacío o solo espacios
- **Esperado:** `INVALID`
- **Obtenido:** `VALID`
- **Causa probable:** falta de validación de nombre nulo/vacío en `Registry.registerVoter`
- **Estado:** Cerrado ✅

## Defecto 06
- **Caso:** registrar la misma persona (id=777) dos veces
- **Esperado:** `DUPLICATED`
- **Obtenido:** `VALID`
- **Causa probable:** `Registry` no mantenía estado de IDs ya registrados
- **Estado:** Cerrado ✅
