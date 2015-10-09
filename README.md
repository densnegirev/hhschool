---------------------------

### Задача 1. Минимальное расстояние

---------------------------

Дан набор из N точек на плоскости (для простоты можно считать, что у всех точек целочисленные координаты). Найдите минимальное расстояние между двумя точками из этого набора.

Пример входных данных:
10 10
20 10
20 15


Пример выходных данных:
5

Решение:

Задача решается за O(NlogN) алгоритмом, построенным по схеме "разделяй и властвуй". Он оформлен в виде рекурсивной функции, которой передаётся множество точек; эта рекурсивная функция разбивает это множество пополам, вызывает себя рекурсивно от каждой половины, а затем выполняет объединение ответов. Операция объединения заключается в обнаружении случаев, когда одна точка оптимального решения попала в одну половину, а другая точка — в другую (в этом случае рекурсивные вызовы от каждой из половинок отдельно обнаружить эту пару, конечно, не смогут).

Реализация алгоритма лежит в пакете points.

Ввод данных осуществляестся из файла, имя которого можно передать через аргумент командной строки (по умолчанию имя входного файла -- "input.txt").
Результат выводится на стандартный выходной поток.

---------------------------

### Задача 2. Количество разбиений на k слагаемых

---------------------------

Для данных натуральных чисел n и k определите количество способов представить число n в виде суммы k натуральных слагаемых, если способы, отличающиеся только порядком слагаемых считать одинаковыми.
Программа получает на вход два натуральных числа n и k, не превосходящих 150.

Пример входных данных:
6 3

Пример выходных данных:
3

Решение:

Разобьем все разбиения числа n на k слагаемых на две группы: к первой отнесем все разбиения, в которых участвует число 1, ко второй -- все остальные. Любое разбиение из первой группы взаимно однозначно получается из разбиения числа n-1 на k-1 слагаемых. А из второй группы -- из разбиения числа n-k на k слагаемых. Ответом на задачу будет сумма количеств разбиений числа n-1 на k-1 слагаемых и числа n-k на k слагаемых.
Решение задачи оформлено в виде небольшой рекурсивной функции.

Реализация находится в пакете partition.

Ввод/вывод осуществляется из стандартных потоков.
