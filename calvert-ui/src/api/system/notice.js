import request from '@/utils/request'

// 查询公告列表
export function listNotice(query) {
  return request({
    url: '/system/notice/list',
    method: 'get',
    params: query
  })
}

// 查询公告详细
export function getNotice(noticeId) {
  return request({
    url: '/system/notice/' + noticeId,
    method: 'get'
  })
}

// 新增公告
export function addNotice(data) {
  return request({
    url: '/system/notice',
    method: 'post',
    data: data
  })
}

// 已读公告
export function updateNoticeToRead (data) {
  return request({
    // url: '/system/notice/updateNoticeToRead/' + noticeIds,
    url: '/system/notice/updateNoticeToRead',
    method: 'put',
    data: data
  })
}

// 已读公告
export function updateNoticesToRead (noticeId) {
  return request({
    url: '/system/notice/updateNoticesToRead' + noticeId,
    method: 'put'
  })
}

// 修改公告
export function updateNotice(data) {
  return request({
    url: '/system/notice',
    method: 'put',
    data: data
  })
}

// 删除公告
export function delNotice(noticeId) {
  return request({
    url: '/system/notice/' + noticeId,
    method: 'delete'
  })
}
