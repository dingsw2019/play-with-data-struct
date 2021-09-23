package linkedlistset

import "github.com/dingsw2019/play-with-data-struct/go/07-Set-and-Map/02-LinkedListSet/linkedlist"

type LinkedListSet struct {
	link *linkedlist.LinkedList
}

func New() *LinkedListSet {
	return &LinkedListSet{
		link: linkedlist.New(),
	}
}

func (s *LinkedListSet) Add(e interface{}) {
	if !s.link.Contains(e) {
		s.link.AddFirst(e)
	}
}

func (s *LinkedListSet) Remove(e interface{}) {
	s.link.RemoveElement(e)
}

func (s *LinkedListSet) Contains(e interface{}) bool {
	return s.link.Contains(e)
}

func (s *LinkedListSet) GetSize() int {
	return s.link.GetSize()
}

func (s *LinkedListSet) IsEmpty() bool {
	return s.link.IsEmpty()
}
